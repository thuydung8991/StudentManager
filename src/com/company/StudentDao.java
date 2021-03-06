package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao{
    private static final String STUDENT_FILE_NAME = "student.txt";

        /**
         * save list student to file
         *
         * @param studentList: list student to save
         */
        public void write(List<Student> studentList) {
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                File file = new File(STUDENT_FILE_NAME);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(studentList);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeStream(fos);
                closeStream(oos);
            }
        }

        /**
         * read list student from file
         *
         * @return list student
         */
        public List<Student> read() {
            List<Student> studentList = new ArrayList<>();
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(new File(STUDENT_FILE_NAME));
                ois = new ObjectInputStream(fis);
                studentList = (List<Student>) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.printf("Student list is empty");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                closeStream(fis);
                closeStream(ois);
            }
            return studentList;
        }

        /**
         * close input stream
         *
         * @param is: input stream
         */
        private void closeStream(InputStream is) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * close output stream
         *
         * @param os: output stream
         */
        private void closeStream(OutputStream os) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
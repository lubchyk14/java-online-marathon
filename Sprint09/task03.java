public static void writeFile(String filename, String text) {

        byte[] arrOfBytes = text.getBytes();
        try(FileOutputStream stream = new FileOutputStream(filename)) {
            for (byte b : arrOfBytes){

                String s2 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                stream.write(s2.substring(1).getBytes());
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
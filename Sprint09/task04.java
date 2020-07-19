public static String readFile(String filename)  {

        String s = null;
        try {
            s = java.nio.file.Files.newBufferedReader(java.nio.file.Paths.get(filename)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        FileInputStream fi = new FileInputStream(filename);

        byte [] arr = new byte[s.length()/7];
        int start =0;
        int end = 7;
        for(int i =0;i<arr.length;i++){
            arr[i] = Byte.parseByte(0+s.substring(start,end), 2);
            start =start + 7;
            end = end + 7;
        }

        return new String(arr);

    }
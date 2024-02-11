/*
MIT License

Copyright (c) 2024 Biplab Dutta

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

// TODO
// - parse the document once. 
// - calculate all the four counts while parsing once. Merge all the separate counting functions.
// - Improve the line and word count functions (for files other than TXT files), make it consistent with `wc`.


import java.io.*;
import java.util.*;


public class wc {

    static InputStream inputStream;
    static int[] flags = new int[]{0, 0, 0, 0}; // 0, 1, 2 -> -l, -w, -m, -c
    static Map<Character, Integer> flagArrMap = new HashMap<>();
    static int cmFlag = 0; // 0, 1 -> -c, -m
    static List<String> inputOptions = new ArrayList<>();
    static List<String> inputFiles = new ArrayList<>();
    static long[] total = new long[]{0, 0, 0};


    public static void main(String[] args) throws IOException {
        boolean isFile = false;  // keeps track of start of file names in args

        flagArrMap.put('l', 0);  // mapping options to array locations
        flagArrMap.put('w', 1);
        flagArrMap.put('m', 2);
        flagArrMap.put('c', 3);

        // Separating options and file names
        for (String s : args) {
            if (!isFile && s.startsWith("-")) {
                if (s.length() == 1) {
                    isFile = true;
                    inputFiles.add(s);
                    continue;
                } else if (s.indexOf("-", 1) != -1) { // if '-' present other than at index 0
                    illegalOption('-');
                }
                inputOptions.add(s.substring(1));
            } else {
                isFile = true;
                inputFiles.add(s);
            }
        }

        processOptions(inputOptions);

        if (inputFiles.isEmpty()) { // To handle piped input
            inputStream = System.in;
            processInputStream(inputStream, "");
        } else {
            processFiles(inputFiles);
        }
        System.exit(0);
    }


    private static void processOptions(List<String> inputOptions) {
        for (String s : inputOptions) {
            for (char c : s.toCharArray()) {
                if (!flagArrMap.containsKey(c)) {
                    illegalOption(c);
                }
                if (c == 'm' || c == 'c') { // whichever comes last overrides the previous
                    cmFlag = c == 'm' ? 1 : 0;
                }
                flags[flagArrMap.get(c)] = 1;
            }
        }
    }


    private static void processFiles(List<String> inputFiles) throws IOException {
        for (String s : inputFiles) {
            try {
                inputStream = new FileInputStream(s);
                processInputStream(inputStream, s);
            } catch (FileNotFoundException e) {
                System.out.println("java wc: " + e.getMessage());
            }
        }
        if (inputFiles.size() > 1) {
            printTotal();
        }
    }


    private static byte[] readBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int length;
        while ((length = inputStream.read(data)) != -1) {
            buffer.write(data, 0, length);
        }
        return buffer.toByteArray();
    }


    private static void processInputStream(InputStream inputStream, String inputFile)
            throws IOException {
        byte[] fData = readBytes(inputStream);
        //  0, 1, 2, 3 -> -l, -w, -m, -c
        int lCount = 0, wCount = 0, bCount = 0;

        System.out.print(" ");
        if (flags[0] + flags[1] + flags[2] + flags[3] == 0) {
            lCount = lineCount(new ByteArrayInputStream(fData));
            wCount = wordCount(new ByteArrayInputStream(fData));
            bCount = byteCount(new ByteArrayInputStream(fData));
            total[0] += lCount;
            total[1] += wCount;
            total[2] += bCount;
            System.out.printf("%7d %7d %7d %s %n", lCount, wCount, bCount, inputFile);
        } else {
            if (flags[0] != 0) {
                lCount = lineCount(new ByteArrayInputStream(fData));
                total[0] += lCount;
                System.out.printf("%7d ", lCount);
            }
            if (flags[1] != 0) {
                wCount = wordCount(new ByteArrayInputStream(fData));
                total[1] += wCount;
                System.out.printf("%7d ", wCount);
            }

            if (flags[2] != 0 || flags[3] != 0) {
                if (cmFlag == 1) {
                    bCount = charCount(new ByteArrayInputStream(fData));
                } else {
                    bCount = byteCount(new ByteArrayInputStream(fData));
                }
                total[2] += bCount;
                System.out.printf("%7d ", bCount);
            }

            System.out.printf("%s %n", inputFile);
        }
    }


    private static void printTotal() {
        int temp = flags[0] + flags[1] + flags[2] + flags[3];

        System.out.print(" ");
        if (temp == 0 || temp == 4) {
            System.out.printf("%7d %7d %7d %s %n", total[0], total[1], total[2], "total");
        } else {
            if (total[0] != 0) {
                System.out.printf("%7d ", total[0]);
            }
            if (total[1] != 0) {
                System.out.printf("%7d ", total[1]);
            }
            if (total[2] != 0) {
                System.out.printf("%7d ", total[2]);
            }
            System.out.printf("%s %n", "total");
        }
    }


    private static int wordCount(InputStream inputStream) {
        int wCount = 0;
        try (Scanner scan = new Scanner(inputStream)) {
            while (scan.hasNext()) {
                scan.next();
                wCount++;
            }
        }
        return wCount;
    }


    private static int lineCount(InputStream inputStream) {
        int lCount = 0;
        try (Scanner scan = new Scanner(inputStream)) {
            while (scan.hasNextLine()) {
                scan.nextLine();
                lCount++;
            }
        }
        return lCount;
    }


    private static int charCount(InputStream inputStream) {
        int cCount = 0;
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(inputStream))) {
            while (reader.read() != -1) {
                cCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cCount;
    }


    private static int byteCount(InputStream inputStream) {
        int bCount = 0;
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                bCount += bytesRead;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bCount;
    }


    private static void illegalOption(char option) {
        System.out.printf("java wc : illegal option -- %c %n", option);
        System.out.println(("usage: java wc [-clmw] [file ...]"));
        System.exit(1);
    }
}

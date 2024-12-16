class Main {  
   public static void main(String[] args) {  
      (new Main()).init();  
   }  
 
   void print(Object o) {  
      System.out.println(o);  
   }  
 
   void printt(Object o) {  
      System.out.print(o);  
   }  
 
   void init() {  
      // Encoding message  
      String file = Input.readFile("Original.txt");  
 
      // 1. Substitute something in  
      char[] sub = new char[5];  
      sub[0] = 'a';  
      sub[1] = 'e';  
      sub[2] = 'v';  
      sub[3] = '1';  
      sub[4] = '0';  
 
      char[] sub2 = new char[5];  
      sub2[0] = '\u2660'; // Spade  
      sub2[1] = '\u2661';  
      sub2[2] = '\u2662';  
      sub2[3] = '\u2663';  
      sub2[4] = '\u2664';  
 
      // 1. Substitution  
      String encodedMsg1 = subEncryption(file, sub, sub2);  
      Input.writeFile("Encode1.txt", encodedMsg1);  
 
      // 2. Reverse the text  
      String encodedMsg2 = reverse(encodedMsg1);  
      Input.writeFile("Encode2.txt", encodedMsg2);  
 
      // 3. Caesar cipher  
      String encodedMsg3 = encode(encodedMsg2);  
      Input.writeFile("Encode3.txt", encodedMsg3);  
 
      // 4. Binary  
      String encodedMsg4 = binary(encodedMsg3);  
      Input.writeFile("Encode4.txt", encodedMsg4);  
 
      // 5. Cut in half  
      String encodedMsg5 = cut(encodedMsg4);  
      Input.writeFile("Encode5.txt", encodedMsg5);  
 
      // Decoding message  
      String file2 = Input.readFile("Encode5.txt");  
 
      String decodedMsg1 = uncut(file2);  
      Input.writeFile("Decode1.txt", decodedMsg1);  
 
      String decodedMsg2 = binaryToString(decodedMsg1);  
      Input.writeFile("Decode2.txt", decodedMsg2);  
 
      String decodedMsg3 = decode(decodedMsg2);  
      Input.writeFile("Decode3.txt", decodedMsg3);  
 
      String decodedMsg4 = reverse(decodedMsg3);  
      Input.writeFile("Decode4.txt", decodedMsg4);  
 
      String decodedMsg5 = subEncryption(decodedMsg4, sub2, sub);  
      Input.writeFile("Decode5.txt", decodedMsg5);  
   }  
 
   // Reverse string  
   String reverse(String txt) {  
      String bld = "";  
      for (int x = txt.length() - 1; x >= 0; x--) {  
        bld += txt.charAt(x);  
      }  
      return bld;  
   }  
 
   // Cipher encoding with no wrapping  
   String encode(String txt) {  
      String bld = "";  
      int ascii;  
      System.out.println("Type a number 1-20");  
      ascii = Input.readInt();  
      char ch = '\0';  
      for (int x = 0; x < txt.length(); x++) {  
        ch = txt.charAt(x);  
        ascii = (int) ch;  
        ascii += 1;  
        bld += (char) ascii;  
      }  
      return bld;  
   }  
 
   String binary(String txt) {  
      String bld = "";  
      for (int x = 0; x < txt.length(); x++) {  
        char ch = txt.charAt(x);  
        int ascii = (int) ch;  
        String binary = Integer.toBinaryString(ascii);  
        while (binary.length() < 14) {  
           binary = "0" + binary;  
        }  
        bld += binary;  
      }  
      return bld;  
   }  
 
   String binaryToString(String binary) {  
      String bld = "";  
      for (int x = 0; x < binary.length(); x += 14) {  
        String byteStr = binary.substring(x, x + 14);  
        int ascii = Integer.parseInt(byteStr, 2);  
        bld += (char) ascii;  
      }  
      return bld;  
   }  
 
   String decode(String txt) {  
      String bld = "";  
      int ascii;  
      char ch = '\0';  
      for (int x = 0; x < txt.length(); x++) {  
        ch = txt.charAt(x);  
        ascii = (int) ch;  
        ascii -= 1;  
        bld += (char) ascii;  
      }  
      return bld;  
   }  
 
   // Substitution encoding  
   String subEncryption(String s, char[] sub, char[] sub2) {  
      String bld = "";  
      char ch = '\0';  
      int index = 0;  
      for (int x = 0; x < s.length(); x++) {  
        ch = s.charAt(x);  
        index = indexOf(ch, sub);  
        if (index != -1) {  
           bld += sub2[index];  
        } else {  
           bld += ch;  
        }  
      }  
      return bld;  
   }  
 
   int indexOf(char ch, char[] arry) {  
      for (int x = 0; x < arry.length; x++) {  
        if (arry[x] == ch) {  
           return x;  
        }  
      }  
      return -1;  
   }  
 
   String cut(String txt) {
    int mid = txt.length() / 2; 
    return txt.substring(mid) + txt.substring(0, mid); 
	}
 
	String uncut(String txt) {
    int mid = txt.length() / 2; 
    return txt.substring(mid) + txt.substring(0, mid);
	}
}
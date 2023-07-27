import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Kalkulator");
        System.out.println("============================");
        System.out.println();

        while (true){
            int angka1 = cekInputInteger("pertama"); // Untuk memberikan value bilangan pertama
            int angka2 = cekInputInteger("kedua"); // Untuk memberikan value bilangan kedua

            System.out.printf("Hasil %.2f\n", hitung(angka1, angka2));

            // Untuk memberikan user pilihan untuk keluar program atau tidak
            System.out.print("Apa kamu ingin keluar (Y/n): ");
            String pilih = scanner.next();
            if(pilih.equals("Y")){
                break; // Jika user memilih Y, maka program selesai
            }

            // Untuk membersihkan tampilan CLI
            for (int i = 0; i < 10; i++) {
                System.out.println();
            }
        }
        scanner.close(); // Untuk menutup scanner
    }
    // Fungsi untuk mengecek tipe data yang diinput oleh user berupa integer atau tidak
    public static int cekInputInteger(String urutanBilangan){
        int angka =0;

        while (true){
            System.out.print("Masukkan angka " + urutanBilangan + ": "); // Print berdasarkan parameternya
            try {
                angka = scanner.nextInt();
                break; // Jika input berupa bilangan bulat, keluar dari loop
            } catch (InputMismatchException e) {
                textError("Maaf, input yang ada masukkan tidak valid");
            }
            scanner.next(); // Untuk membersihkan buffer scanner
        }
        return angka;
    }
    // Fungsi untuk menghitung angka1 dan angka2 sesuai dengan input user
    public static double hitung(int angka1, int angka2){
        double hasil = 0;

        while (true){
            System.out.print("Masukkan operator matematika (+,-,*,/): ");
            String operator = scanner.next();

            // Untuk memastikan user telah memasukkan operator yang sesuai
            try {
                switch (operator){
                    case "+":
                        hasil = angka1 + angka2;
                        break;
                    case "-":
                        hasil = angka1 - angka2;
                        break;
                    case "*":
                        hasil = angka1 * angka2;
                        break;
                    case "/":
                        while (true){
                            if(angka2 == 0){
                                textError("Tidak dapat melakukan pembagian dengan nol");
                                System.out.print("Masukkan bilangan kedua: ");
                                angka2 = scanner.nextInt();
                            }
                            else{ break; }
                        }
                        hasil = (double) angka1 / angka2;
                        break;
                    default:
                        throw new Exception(); // Jika operator tidak sesuai, langung melompat ke error
                }
                break; // Jika operator sesuai, maka keluar loop
            }catch (Exception e){
                textError("Tolong masukkan operator yang sesuai");
            }
        }
        return hasil;
    }
    // Untuk mengubah teks menjadi warna merah yang menandakan pesan error.
    public static void textError(String text){
        String redTextColor = "\u001B[31m"; // Untuk merubah warna text CLI menjadi merah
        String defaultTextColor = "\u001B[0m"; // Untuk merubah warna text CLI menjadi putih
        System.out.println(redTextColor + text + defaultTextColor);
    }
}
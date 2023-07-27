import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Menampilkan judul "Kalkulator" dan header pemisah
        System.out.println("Kalkulator");
        System.out.println("============================");
        System.out.println();

        // Loop utama program yang akan berjalan selama "true" (tanpa henti) hingga user memilih untuk keluar
        while (true) {
            // Meminta input bilangan pertama dari pengguna dan memastikan nilai yang dimasukkan adalah bilangan bulat (integer)
            int angka1 = cekInputInteger("pertama");

            // Meminta input bilangan kedua dari pengguna dan memastikan nilai yang dimasukkan adalah bilangan bulat (integer)
            int angka2 = cekInputInteger("kedua");

            // Menghitung hasil dari operasi matematika antara angka1 dan angka2 menggunakan fungsi "hitung"
            // dan menampilkan hasil dengan format desimal dengan 2 angka di belakang koma
            System.out.printf("Hasil %.2f\n", hitung(angka1, angka2));

            // Meminta pilihan dari pengguna apakah ingin keluar dari program (Y/n)
            System.out.print("Apa kamu ingin keluar (Y/n): ");
            String pilih = scanner.next();

            // Jika user memilih "Y", program akan keluar dari loop utama dan selesai
            if (pilih.equals("Y")) {
                break;
            }

            // Membersihkan tampilan CLI dengan mencetak baris kosong sebanyak 10 kali untuk tampilan yang lebih bersih
            for (int i = 0; i < 10; i++) {
                System.out.println();
            }
        }

        scanner.close(); // Menutup scanner setelah program selesai
    }

    // Fungsi `cekInputInteger` memastikan input pengguna merupakan bilangan bulat dan meminta ulang jika input tidak sesuai.
    public static int cekInputInteger(String urutanBilangan) {
        int angka;

        while (true) {
            System.out.print("Masukkan angka " + urutanBilangan + ": "); // Print berdasarkan parameter urutanBilangan
            try {
                angka = scanner.nextInt();
                break; // Jika input berupa bilangan bulat, keluar dari loop
            } catch (InputMismatchException e) {
                textError("Maaf, input yang Anda masukkan tidak valid"); // Tangkap pengecualian jika input tidak sesuai dan tampilkan pesan kesalahan
            }
            scanner.next(); // Untuk membersihkan buffer scanner dan meminta pengguna untuk memasukkan kembali input yang valid
        }
        return angka; // Mengembalikan nilai angka yang valid setelah pengguna memasukkan bilangan bulat
    }

    // Fungsi `hitung` melakukan perhitungan matematika antara `angka1` dan `angka2` berdasarkan operator yang dimasukkan oleh pengguna,
    // memastikan operator valid, dan menangani kesalahan input operator atau pembagian dengan nol.
    public static double hitung(int angka1, int angka2) {
        double hasil;

        while (true) {
            System.out.print("Masukkan operator matematika (+, -, *, /): ");
            String operator = scanner.next();

            try {
                switch (operator) {
                    case "+" -> hasil = angka1 + angka2;
                    case "-" -> hasil = angka1 - angka2;
                    case "*" -> hasil = angka1 * angka2;
                    case "/" -> {
                        // Periksa apakah angka kedua adalah nol, jika ya, minta pengguna untuk memasukkan nilai baru
                        while (true) {
                            if (angka2 == 0) {
                                textError("Tidak dapat melakukan pembagian dengan nol");
                                System.out.print("Masukkan bilangan kedua: ");
                                angka2 = scanner.nextInt();
                            } else {
                                break; // Jika angka kedua bukan nol, keluar dari loop
                            }
                        }
                        hasil = (double) angka1 / angka2;
                    }
                    default ->
                            throw new Exception(); // Jika operator tidak valid, lemparkan pengecualian untuk menangani kesalahan
                }
                break; // Jika operator valid, keluar dari loop
            } catch (Exception e) {
                textError("Tolong masukkan operator yang sesuai"); // Tangkap pengecualian apa pun dan tampilkan pesan kesalahan
            }
        }
        return hasil;
    }

    // Fungsi textError menampilkan pesan error dalam teks merah di Command Line Interface (CLI)
    // dengan menggunakan escape sequence ANSI. Parameter "text" berisi pesan error yang akan ditampilkan dalam teks merah.
    public static void textError(String text) {
        String redTextColor = "\u001B[31m"; // Escape sequence untuk merubah warna teks menjadi merah
        String defaultTextColor = "\u001B[0m"; // Escape sequence untuk mengembalikan warna teks ke default (putih)
        System.out.println(redTextColor + text + defaultTextColor);
    }
}
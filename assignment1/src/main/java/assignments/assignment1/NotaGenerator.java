import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String nama, hp, paket;
        int pilihan, berat, harga;
        LocalDate tanggalTerima, tanggalSelesai;

        System.out.println("Selamat datang di NotaGenerator!");
        do {
            System.out.println("==============Menu==============");
            System.out.println("[1] Generate ID");
            System.out.println("[2] Generate Nota");
            System.out.println("[0] Exit");
            System.out.print("Pilihan : ");
            pilihan = input.nextInt();
            input.nextLine(); 

            if (pilihan == 1) {
                System.out.println("================================");
                System.out.print("Masukkan nama Anda: ");
                nama = input.nextLine();
                System.out.print("Masukkan nomor handphone Anda: ");
                hp = input.nextLine();
                String id = generateID(nama, hp);
                System.out.println("ID Anda : " + id);
            } else if (pilihan == 2) {
                System.out.println("================================");
                System.out.print("Masukkan nama Anda: ");
                nama = input.nextLine();
                System.out.print("Masukkan nomor handphone Anda: ");
                hp = input.nextLine();
                System.out.print("Masukkan tanggal terima (dd/mm/yyyy): ");
                tanggalTerima = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                System.out.print("Masukkan paket laundry: ");
                paket = input.nextLine();
                while (!isValidPaket(paket)) {
                    System.out.println("Paket " + paket + " tidak diketahui");
                    System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    System.out.print("Masukkan paket laundry: ");
                    paket = input.nextLine();
                    if (paket.equals("?")) {
                        showPaket();
                    }
                }
                System.out.print("Masukkan berat cucian Anda [Kg]: ");
                while (!input.hasNextInt()) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    System.out.print("Masukkan berat cucian Anda [Kg]: ");
                    input.next();
                }
                berat = input.nextInt();
                berat = (berat < 2) ? 2 : berat;
                harga = hitungHarga(paket, berat);
                tanggalSelesai = tanggalTerima.plusDays(getWaktuProses(paket));
                String id = generateID(nama, hp);
                System.out.println("Nota Laundry");
                System.out.println("ID    : " + id);
                System.out.println("Paket : " + paket);
                System.out.println("Harga :");
                System.out.println(berat + " kg x " + harga/berat + " = " + harga);
                System.out.println("Tanggal Terima  : " + tanggalTerima.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("Tanggal Selesai : " + tanggalSelesai.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else if (pilihan != 0) {
                System.out.println("================================");
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        } while (pilihan != 0);

        System.out.println("================================");
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }
}

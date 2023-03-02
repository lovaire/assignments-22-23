package assignments.assignment1;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
}

    public static void main(String[] args) {
        int pilihOpsi = -1;
    while (pilihOpsi != 0) {
        printMenu();
        System.out.print("Masukkan pilihan Anda: ");
        pilihOpsi = input.nextInt();

        switch (menuOption) {
            case 0:
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                break;
            case 1:
                generateIdMenu();
                break;
            case 2:
                generateNotaMenu();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                break;
        }
    }
}

    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    
    public static String generateId(String nama, String nomorHP){
        String namaDepan = nama.split(" ")[0];
        String noHP = nomorHP.replaceAll("[^0-9]", ""); 
        int checksum = getChecksum(namaDepan + noHP);
        return null;
    }

   
    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        int hargaPaketPerKg;
        switch (paket) {
            case "Express":
                hargaPaketPerKg = 12000;
                break;
            case "Fast":
                hargaPaketPerKg = 10000;
                break;
            case "Reguler":
                hargaPaketPerKg = 7000;
                break;
            default:
                return "Invalid paket";
        int totalHarga = berat * hargaPaketPerKg;

    // Menghitung tanggal selesai
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormatter.parse(tanggalTerima));
        } catch (ParseException e) {
            return "Invalid tanggalTerima format";
        }
        calendar.add(Calendar.DAY_OF_MONTH, getPaketLamaHari(paket));
        String tanggalSelesai = dateFormatter.format(calendar.getTime());

        // Membuat nota
        StringBuilder notaBuilder = new StringBuilder();
        notaBuilder.append("ID    : ").append(id).append("\n");
        notaBuilder.append("Paket : ").append(paket).append("\n");
        notaBuilder.append("Harga :").append("\n");
        notaBuilder.append(berat).append(" kg x ").append(hargaPaketPerKg).append(" = ").append(totalHarga).append("\n");
        notaBuilder.append("Tanggal Terima  : ").append(tanggalTerima).append("\n");
        notaBuilder.append("Tanggal Selesai : ").append(tanggalSelesai);

        return notaBuilder.toString();
        }
    }
private static int getPaketLamaHari(String paket) {
    switch (paket) {
        case "Express":
            return 1;
        case "Fast":
            return 2;
        case "Reguler":
            return 3;
        default:
            return -1;
    return null;
    }
}

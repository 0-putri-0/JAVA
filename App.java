import java.util.*;

abstract class Circuit {
    protected List<Resistor> resistors;

    public Circuit(List<Resistor> resistors) {
        if (resistors == null || resistors.isEmpty()) {
            System.out.println("tidak boleh kosong");
        }
        this.resistors = resistors;
    }

    public abstract double getTotalResistance();

    public void displayResistors() {
        System.out.println("Daftar Resistor:");
        for (int i = 0; i < resistors.size(); i++) {
            System.out.println("Resistor " + (i + 1) + ": " + resistors.get(i));
        }
    }
}
class Resistor {
    private double resistance;

    public Resistor(double resistance) {
        if (resistance <= 0) {
            System.out.println("nilai resistor harus positif");
        }
        this.resistance = resistance;
    }

    public double getResistance() {
        return resistance;
    }

    public String toString() {
        return resistance + " Ω";
    }
}
class Seri extends Circuit {

    public Seri(List<Resistor> resistors) {
        super(resistors);
    }

    public double getTotalResistance() {
        double total = 0;
        for (Resistor r : resistors) {
            total += r.getResistance();
        }
        return total;
    }
}

class Parallel extends Circuit {

    public Parallel(List<Resistor> resistors) {
        super(resistors);
    }

    public double getTotalResistance() {
        double total = 0;
        for (Resistor r : resistors) {
            total += 1.0 / r.getResistance();
        }
        return 1.0 / total;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Program Perhitungan Resistansi Total ===");
        System.out.print("Masukkan jumlah resistor: ");
        int jumlahResistor = scanner.nextInt();

        List<Resistor> resistorList = new ArrayList<>();
        for (int i = 0; i < jumlahResistor; i++) {
            System.out.print("Masukkan nilai resistansi resistor ke-" + (i + 1) + " (dalam Ohm): ");
            double nilai = scanner.nextDouble();
            resistorList.add(new Resistor(nilai));
        }

        System.out.println("\nPilih jenis rangkaian:");
        System.out.println("1. Seri");
        System.out.println("2. Paralel");
        System.out.print("Pilihan Anda: ");
        int pilihan = scanner.nextInt();

        Circuit circuit;
        if (pilihan == 1) {
            circuit = new Seri(resistorList);
        } else if (pilihan == 2) {
            circuit = new Parallel(resistorList);
        } else {
            System.out.println("pilihan tidak valid");
            scanner.close();
            return;
        }

        System.out.println("\n--- Hasil Perhitungan ---");
        circuit.displayResistors();
        System.out.printf("Resistansi Total: %.2f Ω\n", circuit.getTotalResistance());

        scanner.close();
    }
}

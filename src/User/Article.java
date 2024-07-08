package User;

import java.time.LocalDate;

public class Article {
    private int id;
    private String judul;
    private String isi;
    private String gambar;
    private LocalDate tanggalTerbit;

    public Article(int id, String judul, String isi, String gambar, LocalDate tanggalTerbit) {
        this.id = id;
        this.judul = judul;
        this.isi = isi;
        this.gambar = gambar;
        this.tanggalTerbit = tanggalTerbit;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }

    public String getGambar() {
        return gambar;
    }

    public LocalDate getTanggalTerbit() {
        return tanggalTerbit;
    }
}

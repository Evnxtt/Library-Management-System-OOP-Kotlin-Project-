# 📚 Library Management System (OOP Kotlin Project)

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Kotlin](https://img.shields.io/badge/language-Kotlin-blue)
![License](https://img.shields.io/badge/license-MIT-lightgrey)
![JUnit](https://img.shields.io/badge/testing-JUnit5-orange)

## 🧩 Deskripsi Proyek
Proyek ini merupakan **implementasi konsep Object-Oriented Programming (OOP)** menggunakan bahasa **Kotlin**, yang menggambarkan sistem manajemen perpustakaan sederhana.  
Fitur utama meliputi:
- Pembuatan dan validasi **Member** (anggota) dengan level keanggotaan (REGULAR, GOLD, PLATINUM).
- Pembuatan **Librarian** (penjaga perpustakaan) dengan atribut khusus `staffCode`.
- Pengelolaan **Book** (buku) dengan fitur peminjaman (`loan`) dan pengembalian (`returnOne`).
- Implementasi **interface Loanable**.
- Pengujian otomatis menggunakan **JUnit 5**.
- Studi Kasus 5: **Laporan Ringkas, Konsistensi, dan Negative Tests**.

---

## 🏗️ Struktur Kelas
```
org.example.com.polman.opp.diagram2code
│
├── Person.kt           ← Abstract class untuk Member & Librarian
├── Member.kt           ← Turunan Person dengan atribut level (enum)
├── Librarian.kt        ← Turunan Person dengan atribut staffCode
├── MemberLevel.kt      ← Enum: REGULAR, GOLD, PLATINUM
├── Book.kt             ← Kelas buku, implementasi dari Loanable
├── Loanable.kt         ← Interface dengan fungsi loan()
├── maincheck.kt        ← File eksekusi utama studi kasus
└── test/
    ├── PersonCreationTest.kt
    ├── FeePolicyPolymorphismTest.kt
    ├── BookCreationTest.kt
    └── BookLoanAndReturnTest.kt
```

---

## 🚀 Cara Menjalankan

### 💡 Melalui IntelliJ IDEA
1. Buka proyek di IntelliJ.
2. Klik kanan pada `maincheck.kt` → **Run 'main()'**.
3. Lihat output di console.

### 💻 Melalui Gradle CLI
**Windows**
```bash
gradlew run
```
**macOS/Linux**
```bash
./gradlew run
```

---

## 🧪 Pengujian Otomatis (JUnit 5)

Gunakan perintah berikut untuk menjalankan semua tes otomatis:

```bash
gradlew test
```

### 📄 Cakupan Tes
| Kelas Test | Tujuan |
|-------------|--------|
| **PersonCreationTest** | Validasi `id`/`name`, setter `name` (trim & panjang), `showInfo()` berisi level dan ID |
| **FeePolicyPolymorphismTest** | Mengecek tarif sesuai level (REGULAR/GOLD/PLATINUM/Librarian=0) |
| **BookCreationTest** | Validasi konstruktor Book dan stok awal |
| **BookLoanAndReturnTest** | Pengujian peminjaman, stok habis, dan over-capacity pada `returnOne()` |

📈 **Interpretasi Hasil**
- ✅ **GREEN (Lulus Semua)** → Implementasi sesuai diagram dan kontrak.
- ❌ **FAILED** → Periksa pesan:
  - `"Nama/ID invalid"` → cek `require{}` di konstruktor atau setter.
  - `"Fee salah"` → cek cabang `when(level)`.
  - `"Loan salah"` → pastikan `loan()` hanya mengurangi stok saat `inStock() == true`.
  - `"Over-capacity"` → `returnOne()` harus melempar exception saat stok penuh.

---

## 🧠 Studi Kasus 5 – Laporan & Konsistensi

File `maincheck.kt` menampilkan laporan data keseluruhan:

```
[5] Laporan Ringkas & Konsistensi

=== PERSONS ===
Member[id=M001, name=Ani, level=REGULAR]
Member[id=M002, name=Budi, level=PLATINUM]
Librarian[id=L001, name=Sari, staffCode=LIB-777]

=== BOOKS ===
Book[id=B001, title=Clean Code, author=Robert C. Martin, year=2008, stock=2/2]
...
Semua studi kasus selesai dijalankan.
```

---

## ⚙️ Bonus (+10) — Negative Tests & Dokumentasi Desain

Tambahan di `maincheck.kt`:
- Menguji validasi terhadap:
  - `name` kosong
  - `id` kosong
  - `name` terlalu pendek/panjang
  - `staffCode` kosong
- Semua kasus diharapkan **melempar exception** (bukan boolean false).

### 📘 Catatan Desain
> Pendekatan validasi menggunakan **exception (`IllegalArgumentException`)** dibanding **boolean**.  
> Alasan:
> - Menjamin setiap objek selalu valid sejak dibuat (fail-fast).
> - Mempermudah debugging dengan pesan error yang eksplisit.
> - Menjaga konsistensi data dan mencegah state tidak valid tersebar ke modul lain.
> - Lebih sesuai untuk sistem OOP berbasis domain (Domain-Driven Design).

---

## 🧾 Contoh Output Akhir
```
=== TEMPLATE OOP: LENGKAPI TODO DI KELAS-KELAS TERLEBIH DAHULU ===
Member[id=M001, name=Ani, level=REGULAR]
Member[id=M002, name=Budi, level=PLATINUM]
Librarian[id=L001, name=Sari, staffCode=LIB-777]
Fee REGULAR (3 hari): 3000.0
Fee PLATINUM (3 hari): 1500.0
loan(m1) = true -> stok=1
loan(m2) = true -> stok=0
loan(m1) (habis) = false -> stok=0
OK: exception over-capacity -> Tidak dapat menambah stok melebihi totalCount (2)

[5-BONUS] Negative Tests & Validasi Exception
Caught expected exception (name kosong): name tidak boleh kosong
Caught expected exception (id kosong): id tidak boleh kosong
Caught expected exception (name terlalu pendek): name minimal 2 karakter
Caught expected exception (name terlalu panjang): name maksimal 100 karakter
Caught expected exception (staffCode kosong): staffCode tidak boleh kosong
Semua negative test selesai dijalankan tanpa error fatal.
```

---

## 🏁 Status Akhir
✅ **Semua JUnit Tests: Passed (100%)**  
✅ **Validasi dan Negative Test: Berfungsi**  
✅ **Konsistensi Output: Sesuai Studi Kasus**

---

## 👨‍💻 Pengembang
**Nama:** (Isi nama kamu di sini)  
**Institusi:** POLMAN  
**Mata Kuliah:** Object Oriented Programming (OOP)  
**Bahasa:** Kotlin  
**Testing:** JUnit5 + Gradle

---

## 📦 Lisensi
Proyek ini bersifat open-source dan dapat digunakan untuk pembelajaran.  
Lisensi: MIT License

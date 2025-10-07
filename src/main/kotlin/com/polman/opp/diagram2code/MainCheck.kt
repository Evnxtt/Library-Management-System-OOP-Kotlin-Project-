package org.example.com.polman.opp.diagram2code

fun main() {
    println("=== DEMO STUDI KASUS SISTEM PERPUSTAKAAN ===")

    // ======================================================
    // STUDI KASUS 1 — REGISTRASI & INFORMASI DASAR
    // ======================================================
    println("\n[1] Registrasi Peran & Informasi Dasar")

    val m1 = Member("M001", "Ani", MemberLevel.REGULAR)
    val m2 = Member("M002", "Budi", MemberLevel.PLATINUM)
    val lib = Librarian("L001", "Sari", "LIB-777")

    println(m1.showInfo())
    println(m2.showInfo())
    println(lib.showInfo())

    // Setter name (trim + validasi)
    m1.name = "  Ani Putri  "
    println("Nama m1 (setelah trim): '${m1.name}'")

    // Uji validasi nama kosong
    try {
        Member("M003", " ", MemberLevel.GOLD)
    } catch (e: IllegalArgumentException) {
        println("OK: Deteksi nama kosong -> ${e.message}")
    }

    // ======================================================
    // STUDI KASUS 2 — DENDA POLIMORFIK
    // ======================================================
    println("\n[2] Denda Polimorfik")

    val members = listOf(
        Member("M010", "Rani", MemberLevel.REGULAR),
        Member("M011", "Gina", MemberLevel.GOLD),
        Member("M012", "Pio", MemberLevel.PLATINUM)
    )
    val librarian = Librarian("L009", "Lina", "LIB-001")

    val testDays = listOf(0, 1, 3, 10)
    for (d in testDays) {
        val fees = members.map { it.calculateFee(d) }
        println("Hari keterlambatan $d: REG=${fees[0]}, GOLD=${fees[1]}, PLAT=${fees[2]}, LIB=${librarian.calculateFee(d)}")
    }

    // ======================================================
    // STUDI KASUS 3 — PEMINJAMAN BUKU
    // ======================================================
    println("\n[3] Peminjaman Buku")

    val b1 = Book("B001", "Clean Code", "Robert C. Martin", 2008, 2)
    val b2 = Book("B002", "Refactoring", "Martin Fowler", 2018, 1)

    println(b1.info())
    println(b2.info())

    println("loan(m1) = ${b1.loan(m1)} -> stok=${b1.available()}")
    println("loan(m2) = ${b1.loan(m2)} -> stok=${b1.available()}")
    println("loan(m1) (habis) = ${b1.loan(m1)} -> stok=${b1.available()}")

    println("loan(b2, m1) = ${b2.loan(m1)} -> stok=${b2.available()}")
    println("loan(b2, m2) (habis) = ${b2.loan(m2)} -> stok=${b2.available()}")

    println("b1 inStock()=${b1.inStock()} | b2 inStock()=${b2.inStock()}")

    // ======================================================
    // STUDI KASUS 4 — PENGEMBALIAN & OVER-CAPACITY
    // ======================================================
    println("\n[4] Pengembalian & Over-Capacity")

    val b3 = Book("B003", "Design Patterns", "GoF", 1995, 1)
    val m4 = Member("M100", "Riko", MemberLevel.REGULAR)

    println("Pinjam b3 -> ${b3.loan(m4)} (stok=${b3.available()})")
    b3.returnOne()
    println("Setelah returnOne -> stok=${b3.available()})")

    try {
        b3.returnOne()
        println("ERROR: Over-capacity tidak terdeteksi!")
    } catch (e: IllegalArgumentException) {
        println("OK: Over-capacity terdeteksi -> ${e.message}")
    }

    // ======================================================
    // STUDI KASUS 5 — LAPORAN RINGKAS & KONSISTENSI
    // ======================================================
    println("\n[5] Laporan Ringkas & Konsistensi")

    val persons = listOf(m1, m2, lib, m4)
    val books = listOf(b1, b2, b3)

    println("\n=== PERSONS ===")
    persons.forEach { println(it.showInfo()) }

    println("\n=== BOOKS ===")
    books.forEach { println(it.info()) }

    println("\nSemua studi kasus selesai dijalankan.")


    // ======================================================
    // BONUS STUDI KASUS 5 — NEGATIVE TESTS & VALIDASI
    // ======================================================
    println("\n[5-BONUS] Negative Tests & Validasi Exception")

    // Kasus 1: Member dengan name kosong
    try {
        val invalidName = Member("MX01", "   ", MemberLevel.GOLD)
        println("ERROR: Member dengan nama kosong seharusnya gagal: ${invalidName.showInfo()}")
    } catch (e: IllegalArgumentException) {
        println("Caught expected exception (name kosong): ${e.message}")
    }

    // Kasus 2: Member dengan ID kosong
    try {
        val invalidId = Member("", "Rudi", MemberLevel.REGULAR)
        println("ERROR: Member dengan ID kosong seharusnya gagal: ${invalidId.showInfo()}")
    } catch (e: IllegalArgumentException) {
        println("Caught expected exception (id kosong): ${e.message}")
    }

    // Kasus 3: Name terlalu pendek
    try {
        val shortName = Member("MX03", "A", MemberLevel.PLATINUM)
        println("ERROR: Name terlalu pendek seharusnya gagal: ${shortName.showInfo()}")
    } catch (e: IllegalArgumentException) {
        println("Caught expected exception (name terlalu pendek): ${e.message}")
    }

    // Kasus 4: Name terlalu panjang
    try {
        val longName = "A".repeat(120)
        val tooLong = Member("MX04", longName, MemberLevel.GOLD)
        println("ERROR: Name terlalu panjang seharusnya gagal: ${tooLong.showInfo()}")
    } catch (e: IllegalArgumentException) {
        println("Caught expected exception (name terlalu panjang): ${e.message}")
    }

    // Kasus 5: Librarian tanpa staffCode
    try {
        val invalidLib = Librarian("L999", "Sinta", " ")
        println("ERROR: Librarian tanpa staffCode seharusnya gagal: ${invalidLib.showInfo()}")
    } catch (e: IllegalArgumentException) {
        println("Caught expected exception (staffCode kosong): ${e.message}")
    }

    println("\nSemua negative test selesai dijalankan tanpa error fatal.")
}

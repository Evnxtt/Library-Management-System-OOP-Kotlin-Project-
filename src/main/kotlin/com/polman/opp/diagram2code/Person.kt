package org.example.com.polman.opp.diagram2code

abstract class Person(
    val id: String,
    name: String
) {
    var name: String = name.trim()
        set(value) {
            val trimmed = value.trim()
            validateName(trimmed)
            field = trimmed
        }

    init {
        require(id.isNotBlank()) { "id tidak boleh kosong" }
        validateName(this.name)
    }

    /**
     * Validasi nama:
     * - tidak kosong setelah trim
     * - panjang 2..100
     * Lempar IllegalArgumentException jika tidak valid
     */
    protected fun validateName(n: String) {
        require(n.isNotBlank()) { "name tidak boleh kosong" }
        require(n.trim().length in 2..100) { "name harus 2..100 karakter" }
    }

    abstract fun showInfo(): String

    /**
     * Hitung denda keterlambatan
     * - kontrak: daysLate >= 0 -> hasil >= 0.0
     * - implementasi di subclass (polimorfik)
     */
    abstract fun calculateFee(daysLate: Int): Double
}

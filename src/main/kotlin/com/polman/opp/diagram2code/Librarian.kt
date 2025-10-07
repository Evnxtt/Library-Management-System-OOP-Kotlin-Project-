package org.example.com.polman.opp.diagram2code

class Librarian(
    id: String,
    name: String,
    val staffCode: String
) : Person(id, name) {

    init {
        require(staffCode.isNotBlank()) { "staffCode tidak boleh kosong" }
    }

    override fun showInfo(): String {
        return "Librarian[id=$id, name=$name, staffCode=$staffCode]"
    }

    override fun calculateFee(daysLate: Int): Double {
        require(daysLate >= 0) { "daysLate tidak boleh negatif" }
        return 0.0
    }
}

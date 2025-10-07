package org.example.com.polman.opp.diagram2code

class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: Int,
    val totalCount: Int
) : Loanable {

    private var availableCount: Int = totalCount

    init {
        // Validasi awal
        require(id.isNotBlank()) { "id tidak boleh kosong" }
        require(title.isNotBlank()) { "title tidak boleh kosong" }
        require(author.isNotBlank()) { "author tidak boleh kosong" }
        require(year in 1400..2100) { "year harus antara 1400..2100" }
        require(totalCount >= 0) { "totalCount tidak boleh negatif" }

        availableCount = totalCount
    }

    fun inStock(): Boolean {
        return availableCount > 0
    }

    override fun loan(to: Member): Boolean {
        if (inStock()) {
            availableCount--
            require(availableCount >= 0) { "availableCount tidak boleh negatif" }
            return true
        }
        return false
    }

    fun returnOne() {
        if (availableCount >= totalCount) {
            throw IllegalArgumentException("Return failed: over-capacity (stock full $availableCount/$totalCount)")
        }
        availableCount++
        require(availableCount <= totalCount) {
            "availableCount tidak boleh lebih dari totalCount"
        }
    }

    fun available(): Int {
        return availableCount
    }

    fun info(): String {
        return "Book[id=$id, title=$title, author=$author, year=$year, stock=$availableCount/$totalCount]"
    }
}

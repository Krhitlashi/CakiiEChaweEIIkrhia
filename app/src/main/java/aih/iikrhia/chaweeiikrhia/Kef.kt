package aih.iikrhia.chaweeiikrhia

class Kef {
    var word: String
    var translation: String
    var note: String? = null
    var loanword: String? = null
    var proto: String? = null
    var calque: String? = null

    constructor(word: String, translation: String) {
        this.word = word
        this.translation = translation
    }

    constructor(word: String, translation: String, note: String?) {
        this.word = word
        this.translation = translation
        this.note = note
    }

    constructor(word: String, translation: String, note: String?, loanword: String?) {
        this.word = word
        this.translation = translation
        this.note = note
        this.loanword = loanword
    }

    constructor(word: String, translation: String, note: String?, loanword: String?, proto: String?) {
        this.word = word
        this.translation = translation
        this.note = note
        this.loanword = loanword
        this.proto = proto
    }

    constructor(word: String, translation: String, note: String?, loanword: String?, proto: String?, calque: String?) {
        this.word = word
        this.translation = translation
        this.note = note
        this.loanword = loanword
        this.proto = proto
        this.calque = calque
    }
}
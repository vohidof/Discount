package Model

class MyModel {
    var id:Int? = null
    var name = ""
    var number:String? = null

    constructor(id: Int?, name: String, number: String?) {
        this.id = id
        this.name = name
        this.number = number
    }

    constructor(name: String, number: String?) {
        this.name = name
        this.number = number
    }

    constructor()
}
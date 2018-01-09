package furhatos.app.burger

import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.util.Language

class OrderItem : Intent() {
    @RecordField
    var main: MainItemEntity? = null

    @RecordField
    var side: SideItemEntity? = null

    @RecordField
    var drink: DrinkEntity? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf(
                "I want a large burger with fries",
                "I want a burger",
                "I would like to order a burger",
                "I would like to order a burger with a side of fries",
                "I'd like a salad with side of fries and a pepsi to drink",
                "I want a salad with a pepsi"
        )
    }
}

class MainItemIntent : Intent() {
    @RecordField
    var main: MainItemEntity? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf(
                "a @main",
                "I want a @main",
                "I'd like a @main",
                "gimme a @main"
        )
    }
}

class SideItemIntent : Intent() {
    @RecordField
    var side: SideItemEntity? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf(
                "a @side",
                "I want a @side",
                "I'd like a @side",
                "gimme a @side"
        )
    }
}


class DrinkItemIntent : Intent() {
    @RecordField
    var drink: DrinkEntity? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf(
                "a @drink",
                "I want a @drink",
                "I'd like a @drink",
                "gimme a @drink"
        )
    }
}

class ListCommandsIntent: Intent(){
    override fun getExamples(lang: Language?): List<String> {
        return listOf(
                "what are the options"
        )
    }
}

class MainItemEntity : EnumEntity() {
    override fun getEnum(lang: Language?): List<String> {
        return listOf(
                "salad",
                "burger",
                "cheeseburger"
        )
    }
}

class SideItemEntity : EnumEntity() {
    override fun getEnum(lang: Language?): List<String> {
        return listOf(
                "fries",
                "onion rings",
                "sweet potatoes"
        )
    }
}

class DrinkEntity : EnumEntity() {
    override fun getEnum(lang: Language?): List<String> {
        return listOf(
                "coke",
                "pepsi",
                "milkshake"
        )
    }
}


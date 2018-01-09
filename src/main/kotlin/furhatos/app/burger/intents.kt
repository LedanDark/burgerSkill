package furhatos.app.burger

import furhatos.nlu.EnumEntity
import furhatos.nlu.EnumItem
import furhatos.nlu.Intent
import furhatos.util.Language

class CombinedOrderIntent : Intent() {
    @RecordField
    var main: MainItemEntity? = null

    @RecordField
    var side: SideItemEntity? = null

    @RecordField
    var drink: DrinkEntity? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf(
                "a burger",
                "I want a large burger with fries",
                "I want a burger",
                "I would like to order a burger",
                "I would like to order a burger with a side of fries",
                "I'd like a salad with side of fries and a pepsi to drink",
                "I'd like a cheeseburger",
                "I want a salad with a pepsi"
        )
    }
}

fun makeWantIntentList(marker:String):List<String> {
    return listOf(
            marker,
            "a $marker",
            "I want a $marker",
            "I'd like a $marker",
            "gimme a $marker",
            "I would like a $marker",
            "could I have a $marker",
            "I'll have a $marker",
            "I'll take a $marker"
    )
}

class MainIntent : Intent() {
    @RecordField
    var main: MainItemEntity? = null

    override fun getExamples(lang: Language?): List<String> {
        return makeWantIntentList("@main")
    }
}


class SideIntent : Intent() {
    @RecordField
    var side: SideItemEntity? = null

    override fun getExamples(lang: Language?): List<String> {
        return makeWantIntentList("@side")
    }
}


class DrinkIntent : Intent() {
    @RecordField
    var drink: DrinkEntity? = null

    override fun getExamples(lang: Language?): List<String> {
        return makeWantIntentList("@drink")
    }
}

class ListCommandsIntent : Intent() {
    override fun getExamples(lang: Language?): List<String> {
        return listOf(
                "what are the options",
                "what do you have"
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
                "sweet potato fries"
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


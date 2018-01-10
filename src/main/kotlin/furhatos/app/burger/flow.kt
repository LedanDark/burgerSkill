package furhatos.app.burger

import furhatos.event.senses.SenseUserEnter
import furhatos.event.senses.SenseUserLeave
import furhatos.flow.kotlin.FlowEvent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.nlu.EnumEntity
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.records.GenericRecord.RecordField
import furhatos.skills.SkillGUI


class OrderUpdateEvent(
        @RecordField var main: MainItemEntity?,
        @RecordField var side: SideItemEntity?,
        @RecordField var drink: DrinkEntity?) : FlowEvent()


var drink: DrinkEntity? = null
var main: MainItemEntity? = null
var side: SideItemEntity? = null

var gui = SkillGUI("Burger GUI", "c:\\web")

val Dialog: State = state {
    onEvent<SenseUserLeave> {
        say("goodbye")
        goto(Idle)
    }
}

val Idle: State = state {
    onEntry {
        attendNobody()
    }

    onEvent<SenseUserEnter> {
        attend(it.userId)
        goto(Greeting)
    }
}

val Greeting: State = state(Dialog) {
    onEntry {
        say("Hi there!")
        goto(RequestOrder)
    }
}

val RequestOrder: State = state(Dialog) {
    onEntry {
        ask("How can I help you")
    }

    onResponse<ListCommandsIntent> {
        ask("would you like a burger, a salad or a cheeseburger")
    }

    onResponse<CombinedOrderIntent> {
        main = it.intent.main
        side = it.intent.side
        drink = it.intent.drink
        goto(CheckOrder)
    }
}

val CheckOrder: State = state(Dialog) {
    onEntry {
        send(OrderUpdateEvent(main, side, drink))
        if (main == null) {
            goto(RequestMain)
        } else if (side == null) {
            goto(RequestSide)
        } else if (drink == null) {
            goto(RequestDrink)
        } else {
            goto(OrderPlaced)
        }
    }
}

val RequestMain = state(Dialog) {
    onEntry {
        ask("what main order would you like?")
    }

    onResponse<MainIntent> {
        main = it.intent.main
        goto(CheckOrder)
    }

    onResponse<No> {
        say("order something")
        goto(CheckOrder)
    }

    onResponse<ListCommandsIntent> {
        ask("you can choose between " + SideItemEntity().optionsToText())
    }
}

val RequestSide = state(Dialog) {
    onEntry {
        ask("would you like a side with that?")
    }

    onResponse<SideIntent> {
        side = it.intent.side
        goto(CheckOrder)
    }

    onResponse<No> {
        side = SideItemEntity()
        side!!.value = "none"
        goto(CheckOrder)
    }

    onResponse<Yes> {
        ask("ok, what would you like?")
    }

    onResponse<ListCommandsIntent> {
        ask("we've got " + SideItemEntity().optionsToText())
    }
}

val RequestDrink = state(Dialog) {
    onEntry {
        ask("how about something to drink?")
    }

    onResponse<DrinkIntent> {
        drink = it.intent.drink
        goto(CheckOrder)
    }

    onResponse<No> {
        drink = DrinkEntity()
        drink!!.value = "none"
        goto(CheckOrder)
    }

    onResponse<Yes> {
        ask("ok, what would you like?")
    }

    onResponse<ListCommandsIntent> {
        ask("we have " + DrinkEntity().optionsToText())
    }
}

val OrderPlaced = state(Dialog) {
    onEntry {

        fun nullOrNone(x: EnumEntity?): Boolean {
            return x == null || x.value == "none"
        }

        var orderDescription = "one ${main!!.value}"

        if (!nullOrNone(side) && nullOrNone(drink)) {
            orderDescription = "one ${main!!.value} with a side of ${side!!.value}"
        }

        if (!nullOrNone(side) && !nullOrNone(drink)) {
            orderDescription = "one ${main!!.value} with a side of ${side!!.value} and a ${drink!!.value}"
        }

        if (nullOrNone(side) && !nullOrNone(drink)) {
            orderDescription = "one ${main!!.value} and a ${drink!!.value}"
        }

        say("ok! One $orderDescription coming right up")
    }
}

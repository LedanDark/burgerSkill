package furhatos.app.burger

import furhatos.event.senses.SenseUserEnter
import furhatos.event.senses.SenseUserLeave
import furhatos.flow.kotlin.FlowEvent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.records.GenericRecord.RecordField


class OrderUpdateEvent(
        @RecordField var main: MainItemEntity?,
        @RecordField var side: SideItemEntity?,
        @RecordField var drink: DrinkEntity?) : FlowEvent()


var drink: DrinkEntity? = null
var main: MainItemEntity? = null
var side: SideItemEntity? = null


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

    onResponse<OrderItem> {
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

    onResponse<MainItemIntent> {
        main = it.intent.main
        goto(CheckOrder)
    }

    onResponse<No> {
        say("order something")
        goto(CheckOrder)
    }

    onResponse<ListCommandsIntent> {
        say("you can choose between " + SideItemEntity().optionsToText())
        reentry()
    }
}

val RequestSide = state(Dialog) {
    onEntry {
        ask("would you like a side with that?")
    }

    onResponse<SideItemIntent> {
        side = it.intent.side
        goto(CheckOrder)
    }

    onResponse<No> {
        side = SideItemEntity()
        side!!.value = "none"
        goto(CheckOrder)
    }

    onResponse<ListCommandsIntent> {
        say("you can choose between " + SideItemEntity().optionsToText())
        reentry()
    }
}

val RequestDrink = state(Dialog) {
    onEntry {
        ask("how about something to drink?")
    }

    onResponse<DrinkItemIntent> {
        drink = it.intent.drink
        goto(CheckOrder)
    }

    onResponse<No> {
        drink = DrinkEntity()
        drink!!.value = "none"
        goto(CheckOrder)
    }

    onResponse<ListCommandsIntent> {
        say("you can choose between " + DrinkEntity().optionsToText())
        reentry()
    }
}

val OrderPlaced = state(Dialog) {
    onEntry {
        say("ok! Your order has been placed")
    }
}

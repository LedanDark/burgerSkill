package furhatos.app.burger

import furhatos.event.CustomEvent
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties

class BurgerSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    val drink = DrinkEntity().also { it.value = "none" }
    val main = MainItemEntity().also { it.value = "none" }
    val side = SideItemEntity().also { it.value = "none" }

    val event = OrderUpdateEvent(main, side, drink)

    val customEvent = CustomEvent.Builder(event::class.qualifiedName).buildEvent()

    event::class.declaredMemberProperties.forEach({
        println(it.name)
        customEvent.put(it.name, it.getter.call(event))
    })

    println("customEvent.toJSON() " + customEvent.toJSON())


    Skill.main(args)
}

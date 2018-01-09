typackage furhatos.app.burger

import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class BurgerSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}

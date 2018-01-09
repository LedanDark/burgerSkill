package furhatos.app.burger

import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

val Init = state {
    onEntry {
        ask("Hello world. Do you like robots?")
    }

    onResponse<Yes>{
        say("I like humans.")
    }

}

package com.bootcamp_android.domain.util

sealed class AddResult {

    data class AddRequest(val auth: String,val start: Long,val end: Long,val parking: Int) : AddResult() {

        var authValid = true
        var parkingValid = true
        var endValid = true
        var startValid = true
        var succes = false

        init {
            validate()
        }

        private fun validate() {
            if(auth == "") {
                authValid = false
            }
            if(parking == -1) {
                parkingValid = false
            }
            if(end == -1L) {
                endValid = false
            }
            if(start == -1L) {
                startValid = false
            }

            succes = endValid && startValid && parkingValid
        }
    }

    sealed class AddResponse {
        object FREE : AddResult()
        object SUCCESS : AddResult()
        object BUSY : AddResult()
        object Error : AddResult()
        object ORDER : AddResult()
        object REQUEST : AddResult()
    }
}

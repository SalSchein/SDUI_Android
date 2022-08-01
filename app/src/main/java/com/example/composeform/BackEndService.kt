package com.example.composeform


interface BackEndService {
    fun getPage(path: String, parameters: Map<String, String>): String
}

class FakeBackEndService : BackEndService {
    var fullname: String? = null
    override fun getPage(path: String, parameters: Map<String, String>): String {
        return when(path) {
            "/" -> initialScreen
            "/confirmPrint" -> secondScreen.also {fullname = parameters.get("first_name")+" "+parameters.get("last_name")}
            "/welcome" -> finalScreen
                .replace("##1##", fullname?:"")
                .replace("##2##", parameters.get("first_check")?:"")
                .replace("##3##", parameters.get("second_check")?:"")
            else -> initialScreen
        }
    }
}

val initialScreen = """
        {
            "children" : [
                {   "viewtype" : "TEXT",    "label" : "PRINTING P1"
                },
                {   "viewtype" : "SPACER",   "label" : " ", "height" : 20, "width" : 20
                },
                {   "viewtype" : "TEXT",    "label" : "Recent actions:"
                },
                {   "viewtype" : "TEXT",    "label" : "- Scan 5/2/22 12:25"
                },
                {   "viewtype" : "BUTTON",    "label" : "Test Button", "data" : "/check"
                },
                {   "viewtype" : "SPACER",   "label" : " ", "height" : 20, "width" : 20
                },
                {
                    "viewtype" : "FORM",
                    "children" : [
                        {
                            "viewtype" : "TEXT",
                            "label" : "Personal Information"
                        },
                        {
                            "viewtype" : "TEXTFIELD",
                            "label" : "First",
                            "data" : "first_name"
                        }, {
                            "viewtype" : "TEXTFIELD",
                            "label": "Last",
                            "data" : "last_name"
                        }
                    ],
                    "label" : "Show Next",
                    "data" : "/confirmPrint"
                }
            ]
        }
    """

val secondScreen = """
        {
            "children" : [
                {
                    "viewtype" : "TEXT",
                    "label" : "Form Header"
                },
                {
                    "viewtype" : "FORM",
                    "children" : [
                        {
                            "viewtype" : "TEXT",
                            "label" : "Checkboxes"
                        },
                        {
                            "viewtype" : "CHECKBOX",
                            "label" : "First",
                            "data" : "first_check"
                        }, {
                            "viewtype" : "CHECKBOX",
                            "label": "Last",
                            "data" : "last_check"
                        }
                    ],
                    "label" : "Submit",
                    "data" : "/welcome"
                },
                {   "viewtype" : "BUTTON",    "label" : "Back to P1", "data" : "/"
                }
            ]
        }
"""

val finalScreen = """
        {
            "children" : [
                {
                    "viewtype" : "TEXT",
                    "label" : "Finished"
                },
                {
                    "viewtype" : "TEXT",
                    "label" : "Full name: ##1##"
                },
                {
                    "viewtype" : "TEXT",
                    "label" : "First Checkbox: ##2##"
                }, {
                    "viewtype" : "TEXT",
                    "label": "Last Checkbox: ##3##"
                },
                {   "viewtype" : "BUTTON",    "label" : "Back to P1", "data" : "/"
                }
            ]
        }
"""

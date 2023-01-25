function onClickMainCheckBox(mainCheckBox) {
    let checkBox = document.getElementsByName("listCounter");
    var butDelete = document.getElementById("actionDelete");
    let butEdit = document.getElementById("actionEdit");

    if(mainCheckBox.checked===true){
        butDelete.removeAttribute("disabled");
        butEdit.removeAttribute("disabled");
        for(let i = 0; i < checkBox.length; i++){
            checkBox[i].checked=true;
        }
    }else{
        butDelete.setAttribute("disabled", "disabled");
        butEdit.setAttribute("disabled", "disabled");
        for(let i = 0; i < checkBox.length; i++){
            checkBox[i].checked=false;
        }
    }
}

function onClickSimpleCheckBox(simpleCheckBox) {
    let checkBox = document.getElementsByName("listCounter");
    let butDelete = document.getElementById("actionDelete");
    let butEdit = document.getElementById("actionEdit");
    let infoCheck;
    for(let i = 0; i < checkBox.length; i++){
        if(checkBox[i].checked){
            infoCheck = true;
        }
    }
    if(infoCheck===true){
        butDelete.removeAttribute("disabled");
        butEdit.removeAttribute("disabled");
    }else{
        butDelete.setAttribute("disabled", "disabled");
        butEdit.setAttribute("disabled", "disabled");
    }
}
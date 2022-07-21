var round = 0;
var newDlg = false;
var curIndex = -1;

function onNext() {
    var children = document.getElementById("orderContainer").children;
    if (curIndex == - 1 || curIndex >= children.length - 1) {
        curIndex = 0;
        round++;
        document.getElementById("round").textContent = round;
    } else {
        curIndex++;
    }
    for (var i = 0; i < children.length; i++) {
        if (i == curIndex) {
            children[i].classList.add("bg-primary");
            children[i].classList.add("text-light");
        } else {
            children[i].classList.remove("bg-primary");
            children[i].classList.remove("text-light");
        }
    }
}

function onEdit() {
    newDlg = false;
    var options = document.getElementById("entryList").options;
    for (var i = 0; i < options.length; i++) {
        if (options[i].selected == true) {
            document.getElementById("name").value = options[i]["_name"];
            document.getElementById("initiative_roll").value = options[i]["_init"];
        }
    }
    var myModal = new bootstrap.Modal(document.getElementById('combatant'));
    myModal.show();
}

function onDelete() {
    var options = document.getElementById("entryList").options;
    for (var i = 0; i < options.length; i++) {
        if (options[i].selected == true) {
            document.getElementById("entryList").remove(i);
        }
    }
}

function onNew() {
    newDlg = true;
    document.getElementById("name").value = "";
    document.getElementById("initiative_roll").value = 0;
    var myModal = new bootstrap.Modal(document.getElementById('combatant'));
    myModal.show();
}

function onReset() {
    round = 0;
    curIndex = -1;
    document.getElementById("round").textContent = round;
    var initList = [];
    var names = {};
    var options = document.getElementById("entryList").options;
    for (var i = 0; i < options.length; i++) {
        var init = options[i]["_init"];
        var name = options[i]["_name"];
        initList.push(init);
        if (!names[init]) {
            names[init] = [];
        }
        names[init].push(name);
    }
    initList.sort(function(a, b) {
        return b - a;
    });
    var container = document.getElementById("orderContainer");
    container.innerHTML = "";
    for (var i = 0; i < initList.length; i++) {
        if (names[initList[i]]) {
            var h = document.createElement("h5");
            h.textContent = names[initList[i]].join(", ");
            container.appendChild(h);
            names[initList[i]] = null;
        }
    }
}

function onOK() {
    var option = null;
    if (newDlg == true) {
        option = document.createElement("option");
        document.getElementById("entryList").add(option);
    } else {
        var options = document.getElementById("entryList").options;
        for (var i = 0; i < options.length; i++) {
            if (options[i].selected == true) {
                option = options[i];
            }
        }
    }
    option.textContent = document.getElementById("name").value + ": " + document.getElementById("initiative_roll").value;
    option["_name"] = document.getElementById("name").value;
    option["_init"] = document.getElementById("initiative_roll").value;
    var myModalEl = document.getElementById('combatant');
    var modal = bootstrap.Modal.getInstance(myModalEl)
    modal.hide();
}
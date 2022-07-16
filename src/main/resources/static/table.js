function onLoad() {
    setActivePage("tables");
}

const onSelectCampaign = async (clicked_id) => {
    var selectedCampaign = document.getElementById("campaign").value;
    const response = await fetch('/tableRoll/' + selectedCampaign + "/tables");
    const jsonResponse = await response.json();
     for (var i = 0; i < 5; i++) {
        document.getElementById("table" + i).innerHTML = '';
     }
    for (t in jsonResponse) {
        for (var i = 0; i < 5; i++) {
            var select = document.getElementById("table" + i);
            var option = document.createElement("option");
            option.textContent =  jsonResponse[t];
            select.appendChild(option);
        }
    }
}

const roll = async (tableNum) => {
    var repeats = document.getElementById('repeats' + tableNum).value;
    var tableId = document.getElementById('table' + tableNum).value;
    var campaignId = document.getElementById("campaign").value;
    var resultsElement = document.getElementById('results' + tableNum);
    while (resultsElement.childElementCount > 0) {
        resultsElement.removeChild(resultsElement.firstChild);
        await sleep(100);
    }

    if (!repeats) {
        repeats = 1;
    }
    const response = await fetch('/tableRoll/' + campaignId + '/' + tableId + '/' + repeats);
    const jsonResponse = await response.json();
    for (r in jsonResponse) {
        resultsElement.appendChild(buildResponseCard(jsonResponse[r]));
    }
}

function buildResponseCard(r) {
    var result = document.createElement("div");
    result.className = "card border bg-light m-1";
    result.style = "width: 15rem;";
    var body = document.createElement("div");
    body.className = "card-body";
    result.appendChild(body);
    var title = document.createElement("h5");
    title.className = "card-title";
    title.textContent = r[0].title;
    body.appendChild(title);
    if (r[0].reference) {
        var reference = document.createElement("h6");
        reference.className = "card-subtitle text-muted";
        reference.textContent = r[0].reference;
        body.appendChild(reference);
    }
    if (r[0].text) {
        var notes = document.createElement("p");
        notes.className = "card-text";
        notes.textContent = r[0].text;
        body.appendChild(notes);
    }
    if (r[0].monsters.length > 0) {
       var button = document.createElement("button");
       button.type = "button";
       button.className = "btn btn-secondary btn-small";
       button.onclick = function() { showCombatStats(r[0].monsters); }
       button.textContent = 'Combat Stats';
       body.appendChild(button);
    }
    return result;
}

function showCombatStats(monsters) {
    var body = document.getElementById("combatStatsBody");
    body.innerHTML = "";
    var table = document.createElement("table");
    var row = document.createElement("row");
    table.appendChild(row);
    body.appendChild(table);
    for (i in monsters) {
        var monster = monsters[i];
        var td = document.createElement("td");
        td.className = "pe-1";
        row.appendChild(td);
        var h5 = document.createElement("h5");
        td.appendChild(h5);
        if (monster.hps.length == 1) {
            h5.textContent = monster.name
        } else {
            h5.textContent = monster.pluralForm
        }
        if (monster.reference) {
            var h6 = document.createElement("h6");
            h6.className = "text-muted";
            h6.textContent = monster.reference;
            td.append(h6);
        }
        if (monster.initiative) {
            var initiative = document.createElement("div");
            initiative.textContent = "Initiative: " + monster.initiative;
            td.append(initiative);
        }
        if (monster.ac) {
            var ac = document.createElement("div");
            ac.textContent = "AC: " + monster.ac;
            td.append(ac);
        }
        var header = document.createElement("div");
        var strong = document.createElement("strong");
        header.appendChild(strong);
        strong.textContent = "Hit Points";
        td.appendChild(header);
        for (i in monster.hps) {
            var hp = document.createElement("div");
            var index = parseInt(i) + 1;
            hp.textContent = index + ": " + monster.hps[i].hp;
            if (monster.hps[i].relativeSize != "normal") {
                hp.textContent += " (" + monster.hps[i].relativeSize + ")";
            }
            td.append(hp);

        }
    }
    var myModal = new bootstrap.Modal(document.getElementById('combatStatsModal'));
    myModal.show();
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

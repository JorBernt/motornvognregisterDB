

const hentBiler = (hentMerke) => {
    $.post("/hentBiler", function(biler){
        for(const bil of biler) {
            let ut = "<option value='"+bil.merke+"'>"+bil.merke+"</option>"
            $("#merke").append(ut);
        }
        if(hentMerke)
            oppdaterType($("#merke").val(), "")
    })
}

const oppdaterType = (merke, spesifikk) => {
    $("#type").find("option").remove();
    $.get("/hentType?merke="+merke, function (data) {
        for(const t of data) {
            let ut = "<option value='"+t+"'>"+t+"</option>"
            $("#type").append(ut);
        }
        if(spesifikk !== "") {
            $("#type").val(spesifikk);
        }
    })

}



const hentAlle = () => {
    $.get("/hentAlle", function(data){
        formaterData(data)
    })
        .fail(status=>{
            if(status.status ="404")
                $("#feilmeldingFelt").html("Du må logge inn for å vise kundene!")
            $("#feilmeldingFelt").css({"color":"red"})
        })
}



const formaterData = bilRegister => {
    $("#alleBiler").find("tr:gt(0)").remove()

    for(const bil of bilRegister) {
        let ut = "<tr>" +
            "<td>"+bil.personNr+"</td>" +
            "<td>"+bil.navn+"</td>" +
            "<td>"+bil.adresse+"</td>" +
            "<td>"+bil.kjennetegn+"</td>" +
            "<td>"+bil.bilmerke+" "+bil.biltype+"</td>" +
            "<td>"+bil.passord+"</td>" +
            "<td><a class='btn btn-primary' href='/endreBil.html?id="+bil.id+"'>Endre</a> </td>" +
            "<td><button class='btn btn-danger' value='"+bil.id+"' onclick='slettLinje(this.value)'>Slett</button> </td>" +
            "</tr>"
        $("#alleBiler").append(ut)
    }
}

$("#slettAlleKnapp").click(()=>{
    $.get("/slettAlle", function (){
        hentAlle()
    })
        .fail(jqXHR => {
            const json = $.parseJSON(jqXHR.responseText)
            $("feilmeldingFelt").html(json.message)
        })
})

const slettLinje = id => {
    $.get("/slettLinje?id="+id, ()=> {
        hentAlle()
    })
        .fail(jqXHR => {
            const json = $.parseJSON(jqXHR.responseText)
            $("feilmeldingFelt").html(json.message)
        })
}

const lagKundeObjekt = (id) => {
    const personNr = $("#personnummerInput")
    const navn = $("#navnInput")
    const adresse = $("#adresseInput")
    const kjennetegn = $("#kjennetegnInput")
    const bilmerke = $("#merke")
    const biltype = $("#type")
    const passord = $("#passord")

    const bil = {
        id : id,
        personNr : personNr.val(),
        navn : navn.val(),
        adresse : adresse.val(),
        kjennetegn : kjennetegn.val(),
        bilmerke : bilmerke.val(),
        biltype : biltype.val(),
        passord : passord.val()
    }

    personNr.val("")
    navn.val("")
    adresse.val("")
    kjennetegn.val("")
    bilmerke.selectedIndex = 0
    biltype.selectedIndex = 0
    passord.val("")

    return bil;
}

$("#loggUtKnapp").click(()=>{
    $.get("/loggut", () => {
        window.location.href = "/";
    })
})



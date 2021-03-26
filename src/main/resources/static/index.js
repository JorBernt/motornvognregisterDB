$(()=> {
    hentBiler();
    hentAlle();
})

const hentBiler = () => {
    $.post("/hentBiler", function(biler){
        for(const bil of biler) {
            let ut = "<option value='"+bil.merke+"'>"+bil.merke+"</option>"
            $("#merke").append(ut);

        }
        oppdaterType($("#merke").val())

    })

}

const oppdaterType = merke => {
    $("#type").find("option").remove();
    $.get("/hentType?merke="+merke, function (data) {
        for(const t of data) {
            let ut = "<option value='"+t+"'>"+t+"</option>"
            $("#type").append(ut);
        }
    })
}

$("#registrerKnapp").click(() => {
    const personNr = $("#personnummerInput")
    const navn = $("#navnInput")
    const adresse = $("#adresseInput")
    const kjennetegn = $("#kjennetegnInput")
    const bilmerke = $("#merke")
    const biltype = $("#type")


    const bil = {
        personNr : personNr.val(),
        navn : navn.val(),
        adresse : adresse.val(),
        kjennetegn : kjennetegn.val(),
        bilmerke : bilmerke.val(),
        biltype : biltype.val()
    }

    $.post("/lagre", bil,() => {
        hentAlle()
    })

    personNr.val("")
    navn.val("")
    adresse.val("")
    kjennetegn.val("")
    bilmerke.selectedIndex = 0
    biltype.selectedIndex = 0

})


const hentAlle = () => {
    $.get("/hentAlle", function(data){
        formaterData(data)
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
            "</tr>"
        $("#alleBiler").append(ut)
    }
}

$("#slettAlleKnapp").click(()=>{
    $.get("/slettAlle", function (){
        hentAlle()
    })
})


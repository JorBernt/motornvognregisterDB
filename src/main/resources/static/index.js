$(()=> {
    hentBiler();
    hentAlle();
    const url = $(location).attr("href");
    if(url.includes("endre"))
        endreFelt();
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

    const bil = lagKundeObjekt(0);

    $.post("/lagre", bil,() => {
        window.location.href = "index.html";
    })



})

const lagKundeObjekt = (id) => {

    const personNr = $("#personnummerInput")
    const navn = $("#navnInput")
    const adresse = $("#adresseInput")
    const kjennetegn = $("#kjennetegnInput")
    const bilmerke = $("#merke")
    const biltype = $("#type")

    const bil = {
        id : id,
        personNr : personNr.val(),
        navn : navn.val(),
        adresse : adresse.val(),
        kjennetegn : kjennetegn.val(),
        bilmerke : bilmerke.val(),
        biltype : biltype.val()
    }

    personNr.val("")
    navn.val("")
    adresse.val("")
    kjennetegn.val("")
    bilmerke.selectedIndex = 0
    biltype.selectedIndex = 0

    return bil;
}


const hentAlle = () => {
    $.get("/hentAlle", function(data){
        formaterData(data)
    })
}

const endreFelt = () => {
    const id = window.location.href.split("=")[1];
    $.get("/hentEn?id="+id, kunde => {
        $("#personnummerInput").val(kunde.personNr);
        $("#navnInput").val(kunde.navn);
        $("#adresseInput").val(kunde.adresse);
        $("#kjennetegnInput").val(kunde.kjennetegn);
        $("#merke").val(kunde.bilmerke);
        oppdaterType(kunde.bilmerke);
        $("#type").val(kunde.biltype);
    })
}

$("#endreKnapp").click(()=>{
    const id = window.location.href.split("=")[1];
    const kunde = lagKundeObjekt(id)
    $.post("/endreKunde", kunde, ()=> {
        window.location.href = "index.html";
    })
})

const formaterData = bilRegister => {
    $("#alleBiler").find("tr:gt(0)").remove()

    for(const bil of bilRegister) {
        let ut = "<tr>" +
            "<td>"+bil.personNr+"</td>" +
            "<td>"+bil.navn+"</td>" +
            "<td>"+bil.adresse+"</td>" +
            "<td>"+bil.kjennetegn+"</td>" +
            "<td>"+bil.bilmerke+" "+bil.biltype+"</td>" +
            "<td><a class='btn btn-primary' href='endreBil.html?id="+bil.id+"'>Endre</a> </td>" +
            "<td><button class='btn btn-danger' value='"+bil.id+"' onclick='slettLinje(this.value)'>Slett</button> </td>" +
            "</tr>"
        $("#alleBiler").append(ut)
    }
}

$("#slettAlleKnapp").click(()=>{
    $.get("/slettAlle", function (){
        hentAlle()
    })
})

const slettLinje = id => {
    $.get("/slettLinje?id="+id, ()=> {
        hentAlle()
    })
}


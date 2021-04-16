const validerNavn = navn => {
    const regex = /^[a-zæøåA-ZÆØÅ' .\-]{2,50}$/;
    const ut = $("#navnFeil")
    if(!regex.test(navn)) {
        ut.html("Navnet må bestå av 2 til 50 bokstaver");
        ut.css({"color": "red"});
        return false;
    }
    ut.html("");
    return true;
}

const validerPNR = PNR => {
    const regex = /^[\d]{12}$/;
    const ut = $("#persNrFeil")
    if(!regex.test(+PNR)) {
        ut.html("Personnummeret må bestå av 12 siffer.");
        ut.css({"color": "red"});
        return false;
    }
    ut.html("");
    return true;
}

const validerAdresse = adresse => {
    const regex = /^[\da-zæøåA-ZÆØÅ' .\-]{2,50}$/;
    const ut = $("#adresseFeil");
    if(!regex.test(adresse)) {
        ut.html("Adressen må være på mellom 2-50 tegn.");
        ut.css({"color": "red"});
        return false;
    }
    ut.html("");
    return true;
}

const validerKjennetegn = kjennetegn => {
    const regex = /^[\da-zæøåA-ZÆØÅ' .\-]{2,8}$/;
    const ut = $("#kjennetegnFeil")
    if(!regex.test(kjennetegn)) {
        ut.html("Kjennetegn må bestå av 2 til 8 tegn");
        ut.css({"color": "red"});
        return false;
    }
    ut.html("");
    return true;
}



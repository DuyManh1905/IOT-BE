async function getLedStatus(ledNumber, ledStatus){
    var ledBackground = document.querySelector("#led-background-" + ledNumber)

    if(document.querySelector("#led-result-" + ledNumber).innerHTML == 'Loading' ||
        (document.querySelector("#led-result-" + ledNumber).innerHTML == 'sáng' && ledStatus == 0) ||
        (document.querySelector("#led-result-" + ledNumber).innerHTML == 'tắt' && ledStatus == 1))
    {
        if(ledNumber == 1)
            pendingLed1 = false
        if(ledNumber == 2)
            pendingLed2 = false
        document.querySelector("#loading-led" + ledNumber).setAttribute("class", "snippet center-block display-none");
    }

    var ledResult = 'sáng'
    var classes = 'o-vuong'
    if(ledStatus == 1){
        classes += ' led-on'
    }
    else if(ledStatus == 0){
        ledResult = 'tắt'
        classes += ' led-off'
    }

    document.querySelector("#led-result-" + ledNumber).innerHTML = ledResult
    ledBackground.setAttribute('class', classes)
}

async function led_on_off(ledNumber)
{
    if(ledNumber == 1 && pendingLed1 || ledNumber == 2 && pendingLed2)
        return

    document.querySelector("#loading-led" + ledNumber).setAttribute("class", "snippet center-block display-block");
    const data = {}
    if(ledNumber == 1)
    {
        if(ledStatus[0] == 1)
        {
            data.led1 = "off";
        }
        else
        {
            data.led1 = "on";
        }
    }
    else
    {
        if(ledStatus[1] == 1)
        {
            data.led2 = "off"
        }
        else
        {
            data.led2 = "on";
        }
    }
    await sendLedControl(ledNumber, data)
}

async function sendLedControl(ledNumber ,data)
{
    const response = await fetch("/mqtt/api/led",
        {
            method: "POST",
            body: JSON.stringify(data)
        })
}
const temperatures = [0, 0, 0, 0, 0, 0, 0];
const humidities = [0, 0, 0, 0, 0, 0, 0];
const lights = [0, 0, 0, 0, 0, 0, 0];
const labels = ['loading', 'loading', 'loading', 'loading', 'loading', 'loading', 'loading'];
const graphName = "temperatureGraph"
let ledStatus = [0,0]
var myChart2;
drawGraph();
var pendingLed1 = true
var pendingLed2 = true
async function main(){
    setInterval(async () => {
       await getAllData();
    }, 5000);
}
main()

function fetchData(callback) {
    $.ajax({
        url: '/data',
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (result) {
            callback(result); // Gọi callback và truyền dữ liệu về
        },
        error: function (error) {
            console.log(error);
            callback(null); // Gọi callback với giá trị null để xác định lỗi
        }
    });
}

async function getAllData() {
    fetchData(function (data) {
        if (data != null) {
            for (var i = 0; i < 7; i++) {
                temperatures[i] = data[i].temperature;
                humidities[i] = data[i].humidity;
                lights[i] = data[i].light;
                labels[i] = data[i].time;
            }
            ledStatus[0] = data[6].led1;
            ledStatus[1] = data[6].led2;

            getTemperature(temperatures[6]);
            getHumidity(humidities[6]);
            getLightStatus(lights[6]);
            getLedStatus(1, ledStatus[0]);
            getLedStatus(2, ledStatus[1]);

            myChart2.update();
        }
    });
}
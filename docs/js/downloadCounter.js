let request;
let url = 'https://api.github.com/repos/magpie-robins/gpa-calculator-android/releases/latest'
request = new XMLHttpRequest();
request.open('GET', url, true);
request.onload = function () {
    let users = JSON.parse(this.response);
    let output = users['assets'][0]["download_count"];
    console.log(output);
    document.getElementById('download').innerHTML = output;
};
request.send();
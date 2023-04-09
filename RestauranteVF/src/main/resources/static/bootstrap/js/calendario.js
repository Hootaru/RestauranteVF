const currentDate = new Date().toISOString().split("T")[0];
document.querySelector("#fecha-hora").setAttribute("min", currentDate);


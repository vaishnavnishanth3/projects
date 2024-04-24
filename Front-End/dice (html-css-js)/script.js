var randomNumber1 = Math.floor(6*Math.random()) + 1;

var randomNumber2 = Math.floor(6*Math.random()) + 1;

if (randomNumber1>randomNumber2)
{
    var html = "Player 1 Wins";
}

else if (randomNumber2>randomNumber1)
{
    html = "Player 2 Wins";
}

else
{
    html = "Match is Draw";
}

document.querySelector("h1").innerHTML=html;

var image1 = "./images/dice"+randomNumber1+".png";

var image2 = "./images/dice"+randomNumber2+".png";

document.querySelector(".img1").setAttribute("src",image1);

document.querySelector(".img2").setAttribute("src",image2);

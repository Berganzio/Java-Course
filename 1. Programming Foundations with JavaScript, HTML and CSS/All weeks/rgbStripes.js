/* Write a JavaScript program that modifies an image by putting 
 * three vertical stripes on it - a red stripe on the left one third, a green stripe in the middle,
/* and a blue stripe on the right one third.
*/

var img = new SimpleImage("hilton.jpg");
print(img);

for (var pixel of img.values()) {
    if (pixel.getX() < img.getWidth()/3) {
        pixel.setRed(255);
    }
    else if (pixel.getX() > img.getWidth()/3 && pixel.getX() < img.getWidth()/3*2) {
        pixel.setGreen(255);
    }
    else {
        pixel.setBlue(255);
    }
}

// alternative solution

for (var pixel of img.values()) {
    var x = pixel.getX();
    var y = pixel.getY();
    if (x < img.getWidth()/3) {
        pixel.setRed(255);
    }
    else if (x > img.getWidth()/3 && x < img.getWidth()/3*2) {
        pixel.setGreen(255);
    }
    else {
        pixel.setBlue(255);
    }
}

// alternative solution

for (var pixel of img.values()) {
    var x = pixel.getX();
    var y = pixel.getY();
    if (x < img.getWidth()/3) {
        pixel.setRed(255);
    }
    else if (x >= img.getWidth()/3*2 && x <= img.getWidth()) {
        pixel.setBlue(255);
    }
    else {
        pixel.setGreen(255);
    }
}


print(img);
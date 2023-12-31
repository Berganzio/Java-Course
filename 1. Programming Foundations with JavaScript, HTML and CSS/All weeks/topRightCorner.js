// compose this function that has to put a rectangle with given rgb color on the top right corner of the image
function topRightCorner(cornerWidth, cornerHeight, someImage, red, green, blue) {

    for (var pixel of someImage.values()) {
        if (pixel.getX() >= someImage.getWidth() - cornerWidth && pixel.getY() <= cornerHeight) { 
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
    }
    return someImage; 
}

var picture = new SimpleImage("chapel.png");
var result = topRightCorner(30, 60, picture, 255, 255, 0);
print(result);
var picture2 = new SimpleImage("smalllion.jpg");
var result2 = topRightCorner(125, 20, picture2, 255, 0, 0);
print(result2);
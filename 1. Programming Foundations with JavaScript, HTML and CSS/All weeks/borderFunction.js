function setBlack(pixel) {
    pixel.setRed(0);
    pixel.setGreen(0);
    pixel.setBlue(0);
    return pixel;
}

function addBorder(image, thickness) {
    var image = new SimpleImage(image);
    var width = image.getWidth();
    var height = image.getHeight();
    for (var pixel of image.values()) {
        var x = pixel.getX();
        var y = pixel.getY();
        if (x < thickness || x > width - thickness || y < thickness || y > height - thickness) {
            setBlack(pixel);
        }
    }
    print (image);
}
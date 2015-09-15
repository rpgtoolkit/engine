local texture = "icon.png"

state = {}

state.initialize = function ()
    print("Initializing")
    tk.assets.loadTexture(texture)
end

state.pause = function ()
    print("Pausing")
end

state.quit = function ()
    print("Quitting")
end

state.resume = function ()
    print("Resuming")
end


state.render = function ()
    -- Draw texture at 0,0 (bottom left corner of screen)
    tk.draw.texture(texture)

    -- Draw texture at specified x,y
    tk.draw.texture(texture, 123.65, 123)


end

state.update = function ()
    -- We don't have to draw in render - shoul dbe able to draw from anywhere

    -- Draw texture at specified x,y and stretch/compress to width, height
    tk.draw.texture(texture, 500, 300.4, 50.5, 40.5)
end

tk.game.push(state)
print("Hello World")

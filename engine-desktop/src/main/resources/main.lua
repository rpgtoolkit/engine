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
    tk.draw.texture(texture)

    tk.draw.texture(texture, 123.65, 123)
end

state.update = function ()
end

tk.game.push(state)
print("Hello World")

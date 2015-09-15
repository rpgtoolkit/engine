local texture

state = {}

state.initialize = function ()
    print("Initializing")
    texture = tk.assets.loadTexture("icon.png")
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
    tk.draw.texture("icon.png")
end

state.update = function ()
end

tk.game.push(state)
print("Hello World")

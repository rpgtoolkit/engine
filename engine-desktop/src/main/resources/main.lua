state = {}

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
end

state.update = function ()
end

tk.game.push(state)
print("Hello World")

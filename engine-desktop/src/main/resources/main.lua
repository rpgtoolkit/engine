state = {}
state.resume = function ()
    print("Resuming")
end

state.pause = function ()
    print("Pausing")
end

print(tk.game.push(state))
--tk.game.pop()
print("Hello World")

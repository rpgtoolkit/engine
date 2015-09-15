local texture = "icon.png"
local pos_x = 0
local pos_y = 0

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
    -- Clear the last frame
    tk.graphics.clearScreen()

    -- Draw texture at 0,0 (bottom left corner of screen)
    tk.graphics.drawTexture(texture)

    -- Draw texture at specified x,y
    tk.graphics.drawTexture(texture, pos_x, pos_y)


end

state.update = function ()
    -- Basic animation
    pos_x = pos_x + 0.1
    pos_y = pos_y + 0.1

    -- We don't have to draw in render - shoul dbe able to draw from anywhere

    -- Draw texture at specified x,y and stretch/compress to width, height
    tk.graphics.drawTexture(texture, 500, 300.4, 50.5, 40.5)
end

tk.game.push(state)
print("Hello World")

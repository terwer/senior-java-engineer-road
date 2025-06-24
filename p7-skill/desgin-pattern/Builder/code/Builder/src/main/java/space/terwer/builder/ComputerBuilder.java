/*
 *            GNU GENERAL PUBLIC LICENSE
 *               Version 3, 29 June 2007
 *
 *  Copyright (C) 2025 Terwer, Inc. <https://terwer.space/>
 *  Everyone is permitted to copy and distribute verbatim copies
 *  of this license document, but changing it is not allowed.
 */

package space.terwer.builder;

/**
 * @author zhangyue on 2025/6/24
 */
public class ComputerBuilder {
    public static DefaultComputerBuilder builder() {
        return new DefaultComputerBuilder();
    }
}

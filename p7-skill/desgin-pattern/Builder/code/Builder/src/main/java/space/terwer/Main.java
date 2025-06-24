/*
 *            GNU GENERAL PUBLIC LICENSE
 *               Version 3, 29 June 2007
 *
 *  Copyright (C) 2025 Terwer, Inc. <https://terwer.space/>
 *  Everyone is permitted to copy and distribute verbatim copies
 *  of this license document, but changing it is not allowed.
 */

package space.terwer;

import space.terwer.builder.Computer;
import space.terwer.builder.ComputerBuilder;

/**
 * @author zhangyue on 2025/6/24
 */
public class Main {
    public static void main(String[] args) {
        Computer computer = ComputerBuilder.builder()
                .cpu("Intel Core i7")
                .gpu("NVIDIA GeForce RTX 3080")
                .ram("32GB DDR4")
                .hdd("1TB SSD")
                .build();

        System.out.println(computer);
    }
}

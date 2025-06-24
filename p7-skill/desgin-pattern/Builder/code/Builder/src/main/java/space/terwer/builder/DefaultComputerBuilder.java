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
public class DefaultComputerBuilder {
    private Computer computer;

    public DefaultComputerBuilder() {
        this.computer = new Computer();
    }

    public Computer build() {
       return this.computer;
    }

    public DefaultComputerBuilder cpu(String cpu){
        this.computer.setCpu(cpu);
        return this;
    }

    public DefaultComputerBuilder gpu(String gpu){
        this.computer.setGpu(gpu);
        return this;
    }

    public DefaultComputerBuilder ram(String ram){
        this.computer.setRam(ram);
        return this;
    }

    public DefaultComputerBuilder hdd(String hdd){
        this.computer.setHdd(hdd);
        return this;
    }
}

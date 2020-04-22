/**
 *
 *
 * 这个包下面的案例是需要与spring mvc结合一起使用的.
 * (Spring websocket也可以不与spring mvc一起使用.)
 *
 * HelloHandler只是简单的发送一个响应给客户端,如果要发给其它在线
 * 用户,需要处理afterConnectionEstablished方法,记录下所有的用户
 *
 * 也不需要对spring-messaging的依赖,这个依赖主要是处理stomp消息的
 *
 *
 * @author cj
 * @date 2018/11/19
 */
package com.hello;
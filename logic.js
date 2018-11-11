/**
 * Receive a task
 * @param {com.smie.task.Receive} receiveTask
 * @transaction
 */
async function receiveTask(receive) {
    receive.task.owner = receive.receiver;

    const registry = await getAssetRegistry('com.smie.task.Task');
    await registry.update(receive.task);

    // Emit an event for the modified asset.
    let event = getFactory().newEvent('com.smie.task', 'ReceiveEvent');
    event.task = receive.task;
    event.receiver = receive.receiver;
    emit(event);
}

/**
 * Mark a task as finished
 * @param {com.smie.task.Finish} finishTask
 * @transaction
 */
async function finishTask(finish) {
    finish.task.isFinish = true;

    const registry = await getAssetRegistry('com.smie.task.Task');
    await registry.update(finish.task);

    // Emit an event for the modified asset.
    let event = getFactory().newEvent('com.smie.task', 'FinishEvent');
    event.task = finish.task;
    event.worker = finish.worker;
    emit(event);
}
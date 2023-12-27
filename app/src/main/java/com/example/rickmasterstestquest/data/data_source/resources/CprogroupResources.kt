package com.example.rickmasterstestquest.data.data_source.resources

import io.ktor.resources.Resource

@Resource("api")
class CprogroupResources {
    @Resource("rubetek")
    class Rubetek(val parent: CprogroupResources = CprogroupResources()) {
        @Resource("doors")
        class Doors(val parent: Rubetek = Rubetek())

        @Resource("cameras")
        class Cameras(val parent: Rubetek = Rubetek())
    }
}
package cn.fivezha.api.controller;

import cn.fivezha.api.core.response.Result;
import cn.fivezha.api.core.response.ResultGenerator;
import cn.fivezha.api.dto.RoleDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.fivezha.api.entity.RoleDO;
import cn.fivezha.api.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色控制器
 *
 * @author Zoctan
 * @date 2018/05/27
 */
@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping
    public Result add(@RequestBody final RoleDTO roleDTO) {
        this.roleService.save(roleDTO);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.roleService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final RoleDTO roleDTO) {
        this.roleService.update(roleDTO);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable final Long id) {
        final RoleDO role = this.roleService.getById(id);
        return ResultGenerator.genOkResult(role);
    }

    @GetMapping
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<RoleDO> list = this.roleService.listAll();
        final PageInfo<RoleDO> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
